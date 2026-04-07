#!/bin/bash

set -e

echo "🚀 Starting Microflow Simulator Environment..."

echo "📦 Bootstrapping Kind cluster and local registry..."
chmod +x setup-cluster.sh
./setup-cluster.sh

echo "🐇 Installing RabbitMQ Cluster Operator..."
kubectl apply -f https://github.com/rabbitmq/cluster-operator/releases/latest/download/cluster-operator.yml

echo "⏳ Waiting for RabbitMQ Operator to become ready..."
kubectl wait deployment -n rabbitmq-system rabbitmq-cluster-operator --for condition=Available=True --timeout=120s

echo "📦 Provisioning RabbitMQ Cluster..."
kubectl apply -f ../infrastructure/broker/rabbitmq-infra.yaml

echo "⏳ Waiting for RabbitMQ Cluster to become ready..."
kubectl wait rabbitmqcluster -n microflow-simulator rabbitmq-server --for condition=ClusterAvailable=True --timeout=120s

echo "📊 Installing kube-prometheus-stack..."
helm upgrade --install obs-stack oci://ghcr.io/prometheus-community/charts/kube-prometheus-stack \
  --namespace monitoring \
  --create-namespace \
  -f ../infrastructure/monitoring/values-monitoring.yaml \
  --wait

echo "🐳 Building and pushing Docker images to local registry..."
docker compose build
docker compose push

echo "☸️ Deploying Spring Boot APIs via Helm..."

helm upgrade --install producer ../charts/spring-boot-api \
  --namespace microflow-simulator \
  -f ../releases/values-producer.yaml \
  --wait

helm upgrade --install consumer ../charts/spring-boot-api \
  --namespace microflow-simulator \
  -f ../releases/values-consumer.yaml \
  --wait

helm upgrade --install requests ../charts/spring-boot-api \
  --namespace microflow-simulator \
  -f ../releases/values-requests.yaml \
  --wait

echo "✅ Deployment successful. Fetching credentials..."

echo "------------------------------------------------"
echo "🐰 RabbitMQ Management UI (localhost:15672)"
RABBITMQ_USER=$(kubectl get secret rabbitmq-server-default-user -n microflow-simulator -o jsonpath="{.data.username}" | base64 --decode)
RABBITMQ_PASS=$(kubectl get secret rabbitmq-server-default-user -n microflow-simulator -o jsonpath="{.data.password}" | base64 --decode)
echo "Username: $RABBITMQ_USER"
echo "Password: $RABBITMQ_PASS"

echo "------------------------------------------------"

echo "📊 Grafana Dashboard (localhost:32001)"
GRAFANA_PASS=$(kubectl get secret --namespace monitoring -l app.kubernetes.io/component=admin-secret -o jsonpath="{.items[0].data.admin-password}" | base64 --decode)
echo "Username: admin"
echo "Password: $GRAFANA_PASS"
echo "------------------------------------------------"
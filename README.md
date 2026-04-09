# Microflow Simulator

## 📖 Overview
A local Kubernetes-based simulator designed to demonstrate asynchronous microservices communication, observability, and robust deployment strategies.

## 🏗️ Architecture & Stack
This project leverages modern DevOps practices and a Java-based backend.

* **Backend:** Java, Spring Boot
* **Message Broker:** RabbitMQ (deployed via RabbitMQ Cluster Operator)
* **Containerization & Orchestration:** Docker, Kubernetes (Kind)
* **Package Management:** Helm
* **Observability:** Prometheus, Grafana (kube-prometheus-stack)
* **Automation:** Bash scripting

## ⚙️ How It Works
The system consists of three main Spring Boot APIs:
1. **Requests API:** Frequently sends requisitions to the Producer API, using Spring Framework’s @Scheduled annotation.
2. **Producer API:** Configures RabbitMQ queues, exchanges, bindings, and connections.
3. **Consumer API:** Processes messages from the queue using delay to simulate operations on the items received.

## 🚀 Getting Started

### Prerequisites
Before running the project, ensure you have the following installed on your machine (if using Windows, WSL2 with Ubuntu is recommended):
* [Docker](https://docs.docker.com/get-docker/) & Docker Compose
* [Kubectl](https://kubernetes.io/docs/tasks/tools/)
* [Helm](https://helm.sh/docs/intro/install/)
* [Kind (Kubernetes IN Docker)](https://kind.sigs.k8s.io/docs/user/quick-start/)

### Running Locally
To bootstrap the entire environment (cluster, registry, broker, observability, and APIs), simply execute the startup script:

\`\`\`bash
chmod +x start.sh
./start.sh
\`\`\`

The script will automatically build the local Docker images, push them to a local registry, and deploy everything via Helm charts.

### Accessing the Infrastructure
Once the deployment is successful, the script will output the credentials. You can access the local services at:

* **RabbitMQ Management UI:** `http://localhost:15672`
* **Grafana Dashboard:** `http://localhost:32001` (Default user: `admin`)

## 🧹 Teardown
To avoid consuming unnecessary system resources, execute the teardown script:

\`\`\`bash
chmod +x teardown.sh
./teardown.sh
\`\`\`

## 🗺️ Future Improvements (Roadmap)
* **Skaffold Integration:** Replace manual bash orchestration with Skaffold for automated image building (via Jib) and Kubernetes deployment.
* **Observability UI Optimization:** Refactor Grafana configuration to use human-readable instance names, organize custom dashboards into dedicated folders, and potentially disable the default `kube-prometheus-stack` dashboards to declutter the workspace.
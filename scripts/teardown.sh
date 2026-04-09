#!/bin/bash

set -e

echo "🧹 Destroying Microflow Simulator Environment..."

echo "🗑️ Deleting Kind cluster..."
kind delete cluster --name kind

echo "🗑️ Removing local Docker registry..."
docker stop kind-registry || true
docker rm kind-registry || true

echo "🧹 Pruning unused Docker images..."
docker image prune -f

echo "✅ Teardown complete. System resources freed."
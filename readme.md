## K8s course

#### 1. Local build kustomization

```shell
kubectl kustomize ./resource-manifests/.kustomization/ > ./resource-manifests/manifest-sfs.yaml
```

#### 2. Apply kustomization file
```shell
kubectl apply -f ./resource-manifests/manifest.yaml
```
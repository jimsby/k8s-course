{{/*
Create the namespace for database
*/}}
{{- define "my-chart.database-namespace" -}}
{{- printf "%s-%s" .Values.services.database.namespacePrefix .Chart.Name | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create the namespace for userService
*/}}
{{- define "my-chart.userservice-namespace" -}}
{{- printf "%s-%s" .Values.services.userService.namespacePrefix .Chart.Name | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create the namespace for postService
*/}}
{{- define "my-chart.postservice-namespace" -}}
{{- printf "%s-%s" .Values.services.postService.namespacePrefix .Chart.Name | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create labels with date and version
*/}}
{{- define "my-chart.labels" }}
  version: {{ .Chart.Version | quote }}
  appVersion: {{ .Chart.AppVersion | quote }}
  date: {{ now | htmlDate | quote }}
{{- end }}


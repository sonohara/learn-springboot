```sh
gcloud beta functions deploy pubsubCloudBuilds --trigger-resource cloud-builds --trigger-event google.pubsub.topic.publish --set-env-vars SLACK_WEBHOOK_URL=https://hooks.slack.com/services/xxx,APP_URL=xxx
```
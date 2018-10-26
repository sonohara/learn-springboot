const { IncomingWebhook } = require('@slack/client');
const notifyUrl = process.env.SLACK_WEBHOOK_URL;
const appUrl = process.env.APP_URL;
const webhook = new IncomingWebhook(notifyUrl);

exports.pubsubCloudBuilds = (event, callback) => {
  // decode
  const build = JSON.parse(Buffer.from(event.data.data, 'base64').toString());

  // filter tags
  if (!build.hasOwnProperty('tags') || build.tags.indexOf('github-sonohara-_my_springboot') == -1) {
    return callback();
  }

  // create message
  const message = {
    username: 'Cloud Build',
    icon_url: 'https://res.cloudinary.com/duuwnnj7u/image/upload/v1540518059/Container_Builder_pqyrkd.png',
    text: `Build \`${build.id}\``,
    mrkdwn: true,
    attachments: [
      {
        title: 'Build logs',
        title_link: build.logUrl,
        fields: [
          {
            title: 'Tags',
            value: build.tags.map(tag => `\`${tag}\``).join("\n")
          },
          {
            title: 'Status',
            value: build.status
          },
          {
            title: 'Url',
            value: appUrl
          }
        ],
        footer: `Send from ${event.context.resource.name}`,
        footer_icon: 'https://res.cloudinary.com/duuwnnj7u/image/upload/v1540519223/Cloud_Functions_voe1fv.png',
        ts: Date.now()
      }
    ]
  };

  // send message
  webhook.send(message, (error, response) => {
    if (error) {
      console.error(error);
    }
  });

  callback();
};
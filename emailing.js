const nodemailer = require("nodemailer");
const credentials = require('./credentials');

let transporter = nodemailer.createTransport({
    host: "ssl0.ovh.net",
    port: 587,
    secure: false, // upgrade later with STARTTLS
    auth: {
      user: credentials.user,
      pass: credentials.password
    }
  });

  transporter.verify(function(error, success) {
    if (error) {
      console.log(error);
    } else {
      console.log("Server is ready to take our messages");
    }
  });

  var message = {
    from: 'info@qonnect.fr',
    to: 'max.09@outlook.com',
    subject: 'Message title',
    text: 'Plaintext version of the message',
    html: '<p>HTML version of the message</p>',
    attachments: [
      {
      filename: "article.html",
      path: "/Users/max/Documents/GitHub/web2Kindle/resources/article.html"
    },
     {   // utf-8 string as an attachment
      filename: 'text1.txt',
      content: 'hello world!'
  },

    ]
};
transporter.sendMail(message).then((msg)=> console.log(msg))
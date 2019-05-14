const nodemailer = require("nodemailer");

nodemailer.createTransport({
    host: "ssl0.ovh.net",
    port: 587,
    secure: false, // upgrade later with STARTTLS
    auth: {
      user: "info@qonnect.fr",
      pass: "pasfy3RBkgk78sword"
    }
  });
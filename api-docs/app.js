require('dotenv').config();
const express = require('express');
const app = express();
const port = Number(process.env.PORT);
const swaggerUI=require('swagger-ui-express');
const swaggerDocument=require("./swagger.json");

app.use('/',swaggerUI.serve,swaggerUI.setup(swaggerDocument));

app.listen(port, () => console.log(`Server listening on port ${port}...`));
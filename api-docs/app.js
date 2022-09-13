require('dotenv').config();
const express = require('express');
const app = express();
const port = Number(process.env.PORT);
const swaggerUI=require('swagger-ui-express');
const swaggerDocument=require("./swagger.json");
const options={
    customfavIcon:"https://help.apiary.io/images/swagger-logo.png"
};
app.use('/',swaggerUI.serve,swaggerUI.setup(swaggerDocument,options));

app.listen(port, () => console.log(`Server listening on port ${port}...`));
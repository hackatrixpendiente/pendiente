var express = require('express');
var router = express.Router();

var pg = require('pg');
var dbConfig = require('../public/javascripts/database');

/* GET users listing. */
router.post('/', function(req, res, next) {
  var datos = req.body;
  var client = new pg.Client(dbConfig);
  client.connect();
  const query = {
    text: `SELECT *
             FROM "PENDIENTE" p1
            WHERE p1."FLGCATEGORIA" = $1`,
    values: [datos.idcategoria],
  }
  client.query(query, (err,rows)=>{
    if(err) {
      console.log(err);
      res.status(500).json({
          success: false,
          msg: 'Error'
      })
    } else {
      res.status(200).json({
        succes: true,
        msg: 'Success',
        data: rows.rows
      });
    }
  });
});

module.exports = router;

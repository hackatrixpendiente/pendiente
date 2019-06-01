var express = require('express');
var router = express.Router();

var pg = require('pg');
var dbConfig = require('../public/javascripts/database');

router.post('/', function(req, res, next) {
    var client = new pg.Client(dbConfig);
    client.connect();
    const query = {
        text: `SELECT * FROM public.listarempresas()`,
        values: [],
    }
    client.query(query, (err,rows)=>{
        if(err) {
          console.log(err);
          res.status(500).json({
              success: false,
              msg: 'Error'
          })
        } else {
          if(rows.rows[0] != null){
              res.status(200).json({
                  succes: true,
                  msg: 'Success',
                  data: rows.rows
              });
          } else {
              res.status(200).json({
                  succes: true,
                  msg: 'Success'
              });
          }
        }
    });
});

router.post('/producto', function(req, res, next) {
    var datos = req.body;
    var client = new pg.Client(dbConfig);
    client.connect();
    const query = {
        text: `SELECT * FROM public.listarsedesproductos($1)`,
        values: [datos.idSede],
    }
    client.query(query, (err,rows)=>{
        if(err) {
          console.log(err);
          res.status(500).json({
              success: false,
              msg: 'Error'
          })
        } else {
          if(rows.rows[0] != null){
              res.status(200).json({
                  succes: true,
                  msg: 'Success',
                  data: rows.rows
              });
          } else {
              res.status(200).json({
                  succes: true,
                  msg: 'Success'
              });
          }
        }
    });
});

module.exports = router;
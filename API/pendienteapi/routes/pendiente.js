var express = require('express');
var router = express.Router();

var pg = require('pg');
var dbConfig = require('../public/javascripts/database');

router.post('/', function(req, res, next) { // lista de pedidos segun lab(idaula) y usuario(idusuario)
    var datos = req.body;
    var client = new pg.Client(dbConfig);
    client.connect();
    const query = {
        text: `SELECT * 
                 FROM "PENDIENTE" p 
                WHERE p."IDUSUARIO" = $2 AND p."IDAULA" = $1`,
        values: [datos.idaula,datos.idusuario],
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

router.post('/producto/', function(req, res, next) { //aulas del usuario
    var datos = req.body;
    var client = new pg.Client(dbConfig);
    client.connect();
    const query = {
        text: `SELECT *
                 FROM "PENDIENTEPRODUCTO"
                WHERE "IDEMPRESA" = $1`,
        values: [datos.idempresa],
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
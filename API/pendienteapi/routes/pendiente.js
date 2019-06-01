var express = require('express');
var router = express.Router();

var pg = require('pg');
var dbConfig = require('../public/javascripts/database');

router.post('/producto/', function (req, res, next) { //aulas del usuario
    var datos = req.body;
    var client = new pg.Client(dbConfig);
    client.connect();
    const query = {
        text: `SELECT *
                 FROM "PENDIENTEPRODUCTO"
                WHERE "IDEMPRESA" = $1`,
        values: [datos.idempresa],
    }
    client.query(query, (err, rows) => {
        if (err) {
            console.log(err);
            res.status(500).json({
                success: false,
                msg: 'Error'
            })
        } else {
            if (rows.rows[0] != null) {
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

router.post('/registro', function (req, res, next) {
    var datos = req.body;
    var client = new pg.Client(dbConfig);
    client.connect();
    const query = {
        text: `SELECT * FROM public.registrar_pendiente($1, $2, $3, $4, $5, $6)`,
        values: [datos.cantidad, datos.mensaje, datos.idProducto, datos.idUsuario, datos.nombreReceptor, datos.imagenReceptor],
    }
    client.query(query, (err, rows) => {
        if (err) {
            console.log(err);
            res.status(500).json({
                success: false,
                msg: 'Error'
            })
        } else {

            if (rows.rows[0] != null) {
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

router.post('/donar', function (req, res, next) {
    var datos = req.body;
    var client = new pg.Client(dbConfig);
    client.connect();
    const query = {
        text: `SELECT * FROM public.donar_pendiente($1)`,
        values: [datos.idDonacion],
    }
    client.query(query, (err, rows) => {
        if (err) {
            console.log(err);
            res.status(500).json({
                success: false,
                msg: 'Error'
            })
        } else {
            if (rows.rows[0] != null) {
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

router.post('/ultimoPendiente', function (req, res, next) {
    var datos = req.body;
    var client = new pg.Client(dbConfig);
    client.connect();
    const query = {
        text: `SELECT * FROM public.select_primer_pendiente_sede($1, $2)`,
        values: [datos.idSede, datos.idUsuario],
    }
    client.query(query, (err, rows) => {
        if (err) {
            console.log(err);
            res.status(500).json({
                success: false,
                msg: 'Error'
            })
        } else {
            if (rows.rows[0] != null) {
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

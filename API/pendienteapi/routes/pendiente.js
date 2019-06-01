var express = require('express');
var router = express.Router();

var pg = require('pg');
var dbConfig = require('../public/javascripts/database');

router.post('/pendientes', function (req, res, next) {
    var datos = req.body;
    var client = new pg.Client(dbConfig);
    client.connect();
    const query = {
        text: `SELECT * FROM public.select_pendientes()`,
        values: [],
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
        text: `SELECT * FROM public.registrar_pendiente($1, $2, $3, $4)`,
        values: [datos.cantidad, datos.mensaje, datos.idProducto, datos.idUsuario],
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
        text: `SELECT * FROM public.donar_pendiente($1, $2, $3)`,
        values: [datos.idDonacion, datos.nombreReceptor, datos.imagenReceptor],
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

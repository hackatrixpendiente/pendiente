<?php
class M_datos extends  CI_Model{
    function __construct(){
        parent::__construct();
    }
   /*function getDatosSede(){
        $sql = "SELECT s.id_sede,
                        s.nombre as sede,
                        s.direccion,
                        p.nombre as producto,
                            p.precio,
                            p.imagen,
                            p.stock,
                            p.descripcion
                       FROM donacion d,
                           usuario u,
                             producto p,
                             sede s
                       WHERE u.id_usuario = d.id_usuario
                          AND p.id_producto = d.id_producto
                          AND s.id_sede = p.id_sede
                          AND d.estado = 'L'";
        $result = $this->db->query($sql, array());
        return $result->result();
    }*/
}
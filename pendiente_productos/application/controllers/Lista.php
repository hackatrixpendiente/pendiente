<?php defined('BASEPATH') OR exit('No direct script access allowed');

class Lista extends CI_Controller {

    function __construct() {
        parent::__construct();
        $this->load->model('M_datos');
    }

    public function index(){
      //$productos = $this->M_datos->getDatosSede();
      $this->load->view('v_lista');
    }
}


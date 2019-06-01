<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Lista extends CI_Controller {

  function __construct() {
        parent::__construct();
        //$this->load->model('M_datos');
    }

  public function index(){
    //$productos = $this->M_datos->getDatosSede();
    $this->load->view('v_lista');
  }

  /*function returnHome(){
    $data['error'] = EXIT_ERROR;
    $data['msj'] = null;
    try {
      $session = array('pantalla' => 0);
      $this->session->set_userdata($session);
      $data['error'] = EXIT_SUCCESS;
    }catch(Exception $e) {
      $data['msj'] = $e->getMessage();
    }
    echo json_encode($data);
  }*/
}
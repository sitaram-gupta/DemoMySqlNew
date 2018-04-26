<?php


namespace user;


class Authentication
{
    private $EMAIL = "";
    private $PASSWORD = "";

    private $DB_CONNECTION;
    private $servername = "localhost";
    private $username = "root";
    private $password = "";
    private $dbname = "register_login";

    function __construct()
    {
        $this->DB_CONNECTION = mysqli_connect($this->servername, $this->username,
            $this->password, $this->dbname);
    }
	//both
    public function prepare($data)
    {
        if (array_key_exists('email', $data))
            $this->EMAIL = $data['email'];

        if (array_key_exists('password', $data))
            $this->PASSWORD = $data['password'];

    }
	//signup
    function isUserExisted() {
        $sql = "SELECT `email` FROM `userauth` WHERE email = '". $this->EMAIL."' ";

        $result = mysqli_query($this->DB_CONNECTION, $sql);

        if(mysqli_num_rows($result) > 0) {
            return true;
        }else {
            return false;
        }
    }
	//login
    function isUserValidToLogin() {
        $sql = "SELECT `email` FROM `userauth` WHERE email = '". $this->EMAIL."'
         AND password = '".$this->PASSWORD."'";

        $result = mysqli_query($this->DB_CONNECTION, $sql);

        if(mysqli_num_rows($result) > 0) {
            return true;
        }else {
            return false;
        }
    }
}
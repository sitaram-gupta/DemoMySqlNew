<?php


namespace user;


class User
{
    private $NAME;
    private $EMAIL;
    private $PASSWORD;

    private $DB_CONNECTION;
    private $serverName = "localhost";
    private $userNameForDB = "root";
    private $passwordOfUserForDB = "";
    private $databaseName = "register_login";

    function __construct()
    {
        $this->DB_CONNECTION = mysqli_connect($this->serverName,
            $this->userNameForDB, $this->passwordOfUserForDB,
            $this->databaseName);
    }

    function prepare($data) {
        if(array_key_exists('name', $data))
            $this->NAME = $data['name'];
        if(array_key_exists('email', $data))
            $this->EMAIL = $data['email'];
        if(array_key_exists('password', $data))
            $this->PASSWORD = $data['password'];
    }

    function insertNewUserIntoDB () {
        $sql = "INSERT INTO `userauth` ( `name`, `email`, `password`) 
        VALUES ( '" . $this->NAME . "',  
              '" . $this->EMAIL . "', '" . $this->PASSWORD . "')";

        $result = mysqli_query($this->DB_CONNECTION, $sql);

        if ($result) {
            $json['success'] = 1;
            $json['message'] = "Success message";

            echo json_encode($json);
        } else {
            $json['success'] = 0;
            $json['message'] ="Already message";

            echo json_encode($json);
        }


    }


}
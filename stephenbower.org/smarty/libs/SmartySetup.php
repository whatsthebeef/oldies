<?php

/**
 * Project: www.stephenbower.org php smarty mysql
 * Author: johnbower [DOT] uk [AT] gamil [DOT] com>
 * Date: 25th February 2009
 * File: index.php
 * Version: 1.0
 */
require_once('phpconfig.php');

require_once (SMARTY_DIR . 'Smarty.class.php');

// smarty configuration
class SmartySetup extends Smarty { 
    function __construct() {
        $this->template_dir = STEPHENBOWER_DIR . 'smarty/templates';
        $this->compile_dir = STEPHENBOWER_DIR . 'smarty/templates_c';
        $this->config_dir = STEPHENBOWER_DIR . 'smarty/configs';
        $this->cache_dir = STEPHENBOWER_DIR . 'smarty/cache';
    }
}
      
?>
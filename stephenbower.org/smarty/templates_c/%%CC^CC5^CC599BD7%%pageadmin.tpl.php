<?php /* Smarty version 2.6.20, created on 2009-04-01 23:03:42
         compiled from pageadmin.tpl */ ?>
<table id="admintable"> 
 	<tr id="admintableborder">  
   	   	<td id="admintableborder">name</td>
   	   	<td id="admintableborder">url</td>
   	   	<td id="admintableborder">creation date</td>
   	   	<td id="admintableborder"></td>
  	</tr> 
 	<?php unset($this->_sections['pages']);
$this->_sections['pages']['name'] = 'pages';
$this->_sections['pages']['loop'] = is_array($_loop=$this->_tpl_vars['pages']) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
$this->_sections['pages']['show'] = true;
$this->_sections['pages']['max'] = $this->_sections['pages']['loop'];
$this->_sections['pages']['step'] = 1;
$this->_sections['pages']['start'] = $this->_sections['pages']['step'] > 0 ? 0 : $this->_sections['pages']['loop']-1;
if ($this->_sections['pages']['show']) {
    $this->_sections['pages']['total'] = $this->_sections['pages']['loop'];
    if ($this->_sections['pages']['total'] == 0)
        $this->_sections['pages']['show'] = false;
} else
    $this->_sections['pages']['total'] = 0;
if ($this->_sections['pages']['show']):

            for ($this->_sections['pages']['index'] = $this->_sections['pages']['start'], $this->_sections['pages']['iteration'] = 1;
                 $this->_sections['pages']['iteration'] <= $this->_sections['pages']['total'];
                 $this->_sections['pages']['index'] += $this->_sections['pages']['step'], $this->_sections['pages']['iteration']++):
$this->_sections['pages']['rownum'] = $this->_sections['pages']['iteration'];
$this->_sections['pages']['index_prev'] = $this->_sections['pages']['index'] - $this->_sections['pages']['step'];
$this->_sections['pages']['index_next'] = $this->_sections['pages']['index'] + $this->_sections['pages']['step'];
$this->_sections['pages']['first']      = ($this->_sections['pages']['iteration'] == 1);
$this->_sections['pages']['last']       = ($this->_sections['pages']['iteration'] == $this->_sections['pages']['total']);
?> 
 	<tr>  
  	   	<td><a href=<?php echo $this->_tpl_vars['pages'][$this->_sections['pages']['index']]['url']; ?>
><?php echo $this->_tpl_vars['pages'][$this->_sections['pages']['index']]['name']; ?>
</a></td>
     	<td><?php echo $this->_tpl_vars['pages'][$this->_sections['pages']['index']]['url']; ?>
</td>
     	<td><?php echo $this->_tpl_vars['pages'][$this->_sections['pages']['index']]['creationdate']; ?>
</td>
     	<td>
     		<form name='deletepage' method='post' action='index.php?action=deletepage'>
     		<input type='hidden' name='pageid' value='<?php echo $this->_tpl_vars['pages'][$this->_sections['pages']['index']]['pageid']; ?>
'>	
     		<input type='submit' value='delete'>
     		</form>
     	</td>
  	</tr> 
	<?php endfor; endif; ?> 
</table>
<form name='createpage' method='post' action='index.php?action=createpage'>
	<div id="name">
		page name: <input type='text' name='pagename'/>
	</div>
	<div id="value">
		url: <input type='text' name='url'/>
	</div>
	<div id="submit">
		<input type='submit' value='submit'>
	</div>
</form>
<?php /* Smarty version 2.6.20, created on 2009-04-01 23:03:48
         compiled from linkadmin.tpl */ ?>
<table id="admintable"> 
 	<tr id="admintableborder">  
   	   	<td id="admintableborder">name</td>
   	   	<td id="admintableborder">url</td>
   	   	<td id="admintableborder">creation date</td>
   	   	<td id="admintableborder"></td>
  	</tr> 
 	<?php unset($this->_sections['links']);
$this->_sections['links']['name'] = 'links';
$this->_sections['links']['loop'] = is_array($_loop=$this->_tpl_vars['links']) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
$this->_sections['links']['show'] = true;
$this->_sections['links']['max'] = $this->_sections['links']['loop'];
$this->_sections['links']['step'] = 1;
$this->_sections['links']['start'] = $this->_sections['links']['step'] > 0 ? 0 : $this->_sections['links']['loop']-1;
if ($this->_sections['links']['show']) {
    $this->_sections['links']['total'] = $this->_sections['links']['loop'];
    if ($this->_sections['links']['total'] == 0)
        $this->_sections['links']['show'] = false;
} else
    $this->_sections['links']['total'] = 0;
if ($this->_sections['links']['show']):

            for ($this->_sections['links']['index'] = $this->_sections['links']['start'], $this->_sections['links']['iteration'] = 1;
                 $this->_sections['links']['iteration'] <= $this->_sections['links']['total'];
                 $this->_sections['links']['index'] += $this->_sections['links']['step'], $this->_sections['links']['iteration']++):
$this->_sections['links']['rownum'] = $this->_sections['links']['iteration'];
$this->_sections['links']['index_prev'] = $this->_sections['links']['index'] - $this->_sections['links']['step'];
$this->_sections['links']['index_next'] = $this->_sections['links']['index'] + $this->_sections['links']['step'];
$this->_sections['links']['first']      = ($this->_sections['links']['iteration'] == 1);
$this->_sections['links']['last']       = ($this->_sections['links']['iteration'] == $this->_sections['links']['total']);
?> 
 	<tr>  
  	   	<td><a href=<?php echo $this->_tpl_vars['links'][$this->_sections['links']['index']]['url']; ?>
><?php echo $this->_tpl_vars['links'][$this->_sections['links']['index']]['name']; ?>
</a></td>
     	<td><?php echo $this->_tpl_vars['links'][$this->_sections['links']['index']]['url']; ?>
</td>
     	<td><?php echo $this->_tpl_vars['links'][$this->_sections['links']['index']]['creationdate']; ?>
</td>
     	<td>
     		<form name='deletelink' method='post' action='index.php?action=deletelink'>
     		<input type='hidden' name='linkid' value='<?php echo $this->_tpl_vars['links'][$this->_sections['links']['index']]['linkid']; ?>
'>	
     		<input type='submit' value='delete'>
     		</form>
     	</td>
  	</tr> 
	<?php endfor; endif; ?> 
</table> 
<form name='createlink' method='post' action='index.php?action=createlink'>
	<div id='name'>link name: <input type='text' name='linkname'/></div>
	<div id='value'>link url: <input type='text' name='url'/></div>
	<div id='dropdown'>
		<select name='page'>
		<?php $_from = $this->_tpl_vars['pages']; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array'); }if (count($_from)):
    foreach ($_from as $this->_tpl_vars['page']):
?>
			<option><?php echo $this->_tpl_vars['page']; ?>
</option>
		<?php endforeach; endif; unset($_from); ?>
		</select>
	</div>
	<div id='submit'><input type='submit' value='submit'></div>
</form>
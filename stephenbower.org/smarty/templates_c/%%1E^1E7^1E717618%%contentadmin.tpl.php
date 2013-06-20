<?php /* Smarty version 2.6.20, created on 2009-04-01 23:03:50
         compiled from contentadmin.tpl */ ?>
<table id="admintable"> 
 	<tr id="admintableborder">  
   	   	<td id="admintableborder">name</td>
   	   	<td id="admintableborder">content</td>
   	   	<td id="admintableborder">creation date</td>
   	   	<td id="admintableborder"></td>
  	</tr> 
 	<?php unset($this->_sections['content']);
$this->_sections['content']['name'] = 'content';
$this->_sections['content']['loop'] = is_array($_loop=$this->_tpl_vars['content']) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
$this->_sections['content']['show'] = true;
$this->_sections['content']['max'] = $this->_sections['content']['loop'];
$this->_sections['content']['step'] = 1;
$this->_sections['content']['start'] = $this->_sections['content']['step'] > 0 ? 0 : $this->_sections['content']['loop']-1;
if ($this->_sections['content']['show']) {
    $this->_sections['content']['total'] = $this->_sections['content']['loop'];
    if ($this->_sections['content']['total'] == 0)
        $this->_sections['content']['show'] = false;
} else
    $this->_sections['content']['total'] = 0;
if ($this->_sections['content']['show']):

            for ($this->_sections['content']['index'] = $this->_sections['content']['start'], $this->_sections['content']['iteration'] = 1;
                 $this->_sections['content']['iteration'] <= $this->_sections['content']['total'];
                 $this->_sections['content']['index'] += $this->_sections['content']['step'], $this->_sections['content']['iteration']++):
$this->_sections['content']['rownum'] = $this->_sections['content']['iteration'];
$this->_sections['content']['index_prev'] = $this->_sections['content']['index'] - $this->_sections['content']['step'];
$this->_sections['content']['index_next'] = $this->_sections['content']['index'] + $this->_sections['content']['step'];
$this->_sections['content']['first']      = ($this->_sections['content']['iteration'] == 1);
$this->_sections['content']['last']       = ($this->_sections['content']['iteration'] == $this->_sections['content']['total']);
?> 
 	<tr>  
  	   	<td><?php echo $this->_tpl_vars['content'][$this->_sections['content']['index']]['name']; ?>
</td>
     	<td><?php echo $this->_tpl_vars['content'][$this->_sections['content']['index']]['content']; ?>
</td>
     	<td><?php echo $this->_tpl_vars['content'][$this->_sections['content']['index']]['creationdate']; ?>
</td>
     	<td>
     		<form name='deletecontent' method='post' action='index.php?action=deletecontent'>
     		<input type='hidden' name='contentid' value='<?php echo $this->_tpl_vars['content'][$this->_sections['content']['index']]['contentid']; ?>
'>	
     		<input type='submit' value='delete'>
     		</form>
     	</td>
  	</tr> 
	<?php endfor; endif; ?> 
</table> 
<form name='createcontent' method='post' action='index.php?action=createcontent'>
	<div id='name'>content name: <input type='text' name='contentname'/></div>
	<div id='value'><textarea name='content' rows='10' cols='60'>enter content:</textarea></div>
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
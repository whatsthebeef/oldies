<?php /* Smarty version 2.6.20, created on 2009-04-02 00:07:45
         compiled from imageadmin.tpl */ ?>
<table id="admintable"> 
 	<tr id="admintableborder">  
   	   	<td id="admintableborder">name</td>
   	   	<td id="admintableborder">images</td>
   	   	<td id="admintableborder">creation date</td>
   	   	<td id="admintableborder"></td>
  	</tr> 
 	<?php unset($this->_sections['images']);
$this->_sections['images']['name'] = 'images';
$this->_sections['images']['loop'] = is_array($_loop=$this->_tpl_vars['images']) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
$this->_sections['images']['show'] = true;
$this->_sections['images']['max'] = $this->_sections['images']['loop'];
$this->_sections['images']['step'] = 1;
$this->_sections['images']['start'] = $this->_sections['images']['step'] > 0 ? 0 : $this->_sections['images']['loop']-1;
if ($this->_sections['images']['show']) {
    $this->_sections['images']['total'] = $this->_sections['images']['loop'];
    if ($this->_sections['images']['total'] == 0)
        $this->_sections['images']['show'] = false;
} else
    $this->_sections['images']['total'] = 0;
if ($this->_sections['images']['show']):

            for ($this->_sections['images']['index'] = $this->_sections['images']['start'], $this->_sections['images']['iteration'] = 1;
                 $this->_sections['images']['iteration'] <= $this->_sections['images']['total'];
                 $this->_sections['images']['index'] += $this->_sections['images']['step'], $this->_sections['images']['iteration']++):
$this->_sections['images']['rownum'] = $this->_sections['images']['iteration'];
$this->_sections['images']['index_prev'] = $this->_sections['images']['index'] - $this->_sections['images']['step'];
$this->_sections['images']['index_next'] = $this->_sections['images']['index'] + $this->_sections['images']['step'];
$this->_sections['images']['first']      = ($this->_sections['images']['iteration'] == 1);
$this->_sections['images']['last']       = ($this->_sections['images']['iteration'] == $this->_sections['images']['total']);
?> 
 	<tr>  
  	   	<td><?php echo $this->_tpl_vars['images'][$this->_sections['images']['index']]['name']; ?>
</td>
     	<td><?php echo $this->_tpl_vars['images'][$this->_sections['images']['index']]['images']; ?>
</td>
     	<td><?php echo $this->_tpl_vars['images'][$this->_sections['images']['index']]['creationdate']; ?>
</td>
     	<td>
     		<form name='deleteimage' method='post' action='index.php?action=deleteimage'>
     		<input type='hidden' name='imageid' value='<?php echo $this->_tpl_vars['images'][$this->_sections['images']['index']]['imageid']; ?>
'>	
     		<input type='submit' value='delete'>
     		</form>
     	</td>
  	</tr> 
	<?php endfor; endif; ?> 
</table> 
<form method="post" enctype="multipart/form-data"' action='index.php?action=createimage'>
	<div id='value'>
		<input type="hidden" name="MAX_FILE_SIZE" value="2000000">
		<input name="userfile" type="file" id="userfile">
		<input name="upload" type="submit" class="box" id="upload" value="Upload">
	</div>
</form>
<form name='createimage' method='post' action='index.php?action=createimage'>
	<div id='name'>image name: <input type='text' name='imagename'/></div>
	<div id='value'><textarea name='image' rows='10' cols='60'>enter image label:</textarea></div>
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
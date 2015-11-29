	$(document)
			.ready(
					function() {
						$("#divUpdate").hide();
						$("#divAdd").hide();
						

						$('#device_table')
								.DataTable(
										{
											stateSave : true,
											
											"ajax" : {
												"url" : "api/device",
												"dataSrc" : ""
											},
											"columns" : [
													{ "data" : "deviceType.deviceType" },
													{ "data" : "deviceIp" },
													{ "data" : "deviceName" },
													{ "data" : "switchEnable" }
													 ],
											"columnDefs": [
											               {
											                   "render": function ( data, type, row ) {
											                	   if (data === true){
											                       return "Enable";
											                       }else{
											                    	   return "Disable";
											                       }
											                   },
											                   "targets": -1
											               }
											               ]		 
										});

						
	
						
						$('#device_table tbody')
								.on(
										'click',
										'tr',
										function() {
											var table = $('#device_table').DataTable();
											if ($(this).hasClass('selected')) {
												$(this).removeClass('selected');
											} else {
												table
														.$('tr.selected')
														.removeClass('selected');
												$(this).addClass('selected');
											}
											/*
											if ($("#divAdd").is(':hidden') == false) {
												$("#divAdd").hide();
											}
*/
											if ($('#divUpdate').is(':visible') == true) {
												
												//$('#button_del').show();
												var device = table.row('.selected').data();
												edit_refresh(device);
												
											}
										});

	
						$('#button_del')
								.click(
										function() {
											var table = $('#device_table').DataTable();
											$("#divUpdate").hide();
											//$("#divAdd").hide();
											var id_delete = table.row(
													'.selected').id();
											if (id_delete == null)
												return;
											console.log("delete id= "
													+ id_delete);

											$.ajax({
												type : "DELETE",
												url : "api/device/delete/"
														+ id_delete,
												success : function(value) {

													table.row('.selected')
															.remove().draw(
																	false);
												}
											});

										});

						var counter = 1;

						$('#btn_update')
								.click(
										function() {
											var table = $('#device_table').DataTable();
											if (!$('#btn_update').val()) {
												alert("Выберите устройство");
												return;
											}
											var uid = $('#btn_update').val();
											var udevice = {
												DT_RowId : $('#btn_update').val(),
												deviceIp : $("#uip_device").val(),
												deviceName : $("#uhostname_device").val(),
												deviceUsername : $("#ulogin_device").val(),
												devicePassw : $("#upasswd_device").val(),
												switchEnable : $("#uenable_device").val(),
												deviceType :{
													id : $("#utype_device").val(),
													deviceType : $("#utype_device option:selected").text()
												}
											};
											var data = JSON.stringify(udevice);
											console.log(data);
											$.ajax({
														type : "PUT",
														url : "api/device",
														data : data,
														contentType : "application/json; charset=utf-8",
														dataType : "json",
														success : function(
																value) {
															table.row("#"+ uid).data(value);

														}
													});
											
										})

						$('#button_add').on('click', function() {
							if ($("#divUpdate").is(':hidden') == false) {
								$("#divUpdate").hide();
							}
							$("#divAdd").show()
							$('#button_del').show();
							 $.getJSON("api/deviceType", function (data) {
							        $.each(data, function (index, value) {
							        	selec = "<option value="+value.id+">"+value.deviceType+"</option>";
							        	console.log(value);
							        	$("#ctype_device").append(selec);
							        	
							        });
							    });
						
						});

						$("#button_edit").on(
								'click',
								function() {
									var table = $('#device_table').DataTable();
									//$('#button_del').hide();
									
									if ($("#divAdd").is(':visible') == true) {
										$("#divAdd").hide();
										
									}
									
									if ($("#divUpdate").is(':visible') == false) {
										$("#divUpdate").show();
										var device = table.row('.selected').data();
										if(device == null){
											$("#divUpdate").hide();
											$('#button_del').show();
											alert("Выберите устройство");
											
										}else{
										edit_refresh(device);
										}
									}else{
										$("#divUpdate").hide();
										//$('#button_del').show();
									}
									
																		    });
									
									
									
function edit_refresh(device){
	$("#utype_device").empty();
	var table = $('#device_table').DataTable();
	 $.getJSON("api/deviceType", function (data) {
		 $.each(data, function (index, value) {
			 if (device != null)
	        	{
	        		$("#uip_device").val(device.deviceIp);
					$("#uhostname_device").val(device.deviceName);
					$("#ulogin_device").val(device.deviceUsername);
					$("#upasswd_device").val(device.devicePassw);
					$("#uenable_device").val(device.switchEnable.toString());
					$("#btn_update").val(table.row('.selected').id());
	        	}
	        	$("#utype_device").append($('<option>', {
	        		value : value.id, 
	        		text: value.deviceType
	        	}));							            
	        });
	    }).done(function() {
	    	if (device != null)
	    	$("#utype_device").val(device.deviceType.id);
	    })
};								
									
								

						$("#btn_create")
								.click(
										function() {
											var table = $('#device_table').DataTable();
											$('#button_del').show();
											
											var cdevice = {
													DT_RowId : "",
													deviceIp : $("#cip_device").val(),
													deviceName : $("#chostname_device").val(),
													deviceUsername : $("#clogin_device").val(),
													devicePassw : $("#cpasswd_device").val(),
													switchEnable : $("#cenable_device").val(),
													deviceType :{
														id : $("#ctype_device").val(),
														deviceType : $("#ctype_device option:selected").text()
													}
											}
											


											var data = JSON.stringify(cdevice);
											console.log(data);
											
											$.ajax({
														type : "POST",
														url : "api/device",
														data : data,
														contentType : "application/json; charset=utf-8",
														dataType : "json",
														success : function(
																value) {

															table.row
																	.add(value)
																	.draw();
															;
														}
													});
											

											
										});
					});
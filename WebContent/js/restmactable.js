	$(document)
			.ready(
					function() {
						var mycolumn_init = [
										{ "data" : "macTable.switchAddress" },
										{ "data" : "macTable.switchName" },
										{ "data" : "macTable.switchPort" },
										{ "data" : "macTable.macAddress" },
										{ "data" : "macTable.timeCollection" }
										 ]
						
						$('#mac_table')
						.DataTable(
								{
																
									"ajax" : {
										"url" : "api/mactablesummary",
										"dataSrc" : ""
									},
									"columns" : mycolumn_init,
									
								     initComplete: function () {
								            this.api().columns().every( function () {
								                var column = this;
								                var select = $('<br><select><option value=""></option></select>')
								                    .appendTo( $(column.header()) )
								                    .on( 'change', function () {
								                        var val = $.fn.dataTable.util.escapeRegex(
								                            $(this).val()
								                        );
								 
								                        column
								                            .search( val ? '^'+val+'$' : '', true, false )
								                            .draw();
								                    } );
								 
								                column.data().unique().sort().each( function ( d, j ) {
								                    select.append( '<option value="'+d+'">'+d+'</option>' )
								                } );
								            } );
								        }
									
									});
						
						
                        $("#button_refresh").click(function(){
    						var mycolumn = [
    												{ "data" : "macTable.switchAddress" },
    												{ "data" : "macTable.switchName" },
    												{ "data" : "macTable.switchPort" },
    												{ "data" : "macTable.macAddress" },
    												{ "data" : "macTable.timeCollection" }
    												 ]

                            var param = "";
                            var number_param = 0;
                            var table = $('#mac_table').DataTable();
            				
                            
                            $(table.table().header().rows[0]).find("th").remove();
                            $(table.table().header().rows[0]).append("<th>Device Ip</th>");
                            $(table.table().header().rows[0]).append("<th>Device Name</th>");
                            $(table.table().header().rows[0]).append("<th>Device Port</th>");
                            $(table.table().header().rows[0]).append("<th>MAC</th>");
                            $(table.table().header().rows[0]).append("<th>Time collection</th>");
                            
                            if($("#check_arp_static").is(":checked")){
                                //console.log("check_arp_static");
                                param = "check_arp_static=yes"
                                	number_param++;
                                $(table.table().header().rows[0]).append("<th>ARP Host IP</th>");
                                $(table.table().header().rows[0]).append("<th>ARP Host Mac</th>");
                                $(table.table().header().rows[0]).append("<th>ARP Hoatname</th>");
                                $(table.table().header().rows[0]).append("<th>ARP Comentary</th>");
                                
                                mycolumn.push({ "data" : "arpTableStatic.hostIp" });
                                mycolumn.push({ "data" : "arpTableStatic.hostMac" });
                                mycolumn.push({ "data" : "arpTableStatic.hostName" });
                                mycolumn.push({ "data" : "arpTableStatic.commentary" });
                            }
                            
                            if($("#check_dhcp_table").is(":checked")){
                                //console.log("check_dhcp_table");
                                if(number_param != 0){
                                    param += "&";
                                }
                                param += "check_dhcp_table=yes"
                                	number_param++;
                                
                                $(table.table().header().rows[0]).append("<th>Dhcp Host IP</th>");
                                $(table.table().header().rows[0]).append("<th>Dhcp Host Name</th>");
                                $(table.table().header().rows[0]).append("<th>Dhcp Host Termination</th>");
                                $(table.table().header().rows[0]).append("<th>Dhcp Host ipType</th>");
                                $(table.table().header().rows[0]).append("<th>Dhcp hostMac</th>");
                                $(table.table().header().rows[0]).append("<th>Dhcp description</th>");
                                $(table.table().header().rows[0]).append("<th>Dhcp securityAccess</th>");
                                $(table.table().header().rows[0]).append("<th>Dhcp duration</th>");
                                $(table.table().header().rows[0]).append("<th>Dhcp profile</th>");
                                $(table.table().header().rows[0]).append("<th>Dhcp politics</th>");
                               
                                
                                mycolumn.push({ "data" : "dhcpTable.hostIP" });
                                mycolumn.push({ "data" : "dhcpTable.hostName" });
                                mycolumn.push({ "data" : "dhcpTable.termination" });
                                mycolumn.push({ "data" : "dhcpTable.ipType" });
                                mycolumn.push({ "data" : "dhcpTable.hostMac" });
                                mycolumn.push({ "data" : "dhcpTable.description" });
                                mycolumn.push({ "data" : "dhcpTable.securityAccess" });
                                mycolumn.push({ "data" : "dhcpTable.duration" });
                                mycolumn.push({ "data" : "dhcpTable.profile" });
                                mycolumn.push({ "data" : "dhcpTable.politics" });
                               
                                
                            }
                        
                                table.destroy();
                              //console.log(mycolumn);
                                var urltable = "api/mactablesummary"
                                    if(number_param != 0){
                                        urltable += "?"+param;
                                    }
                                $('#mac_table')
        						.DataTable(
        								{
        									
        									
        									"ajax" : {
        										"url" : urltable,
        										"dataSrc" : ""
        									},
        									"columns" : mycolumn,
        									
        									initComplete: function () {
    								            this.api().columns().every( function () {
    								                var column = this;
    								                var select = $('<select><option value=""></option></select>')
    								                    .appendTo( $(column.header()))
    								                    .on( 'change', function () {
    								                        var val = $.fn.dataTable.util.escapeRegex(
    								                            $(this).val()
    								                        );
    								 
    								                        column
    								                            .search( val ? '^'+val+'$' : '', true, false )
    								                            .draw();
    								                    } );
    								 
    								                column.data().unique().sort().each( function ( d, j ) {
    								                    select.append( '<option value="'+d+'">'+d+'</option>' )
    								                } );
    								            } );
    								        }
        											 
        								});
                            
                         
                           
                            
                        console.log(urltable);
                      
                        });
					});


function alertBox(val){
	$.alert({
		//icon: 'fa fa-bell-o',
		title: '',
		theme: 'modern',
		closeIcon: true,
		content: val,
		type: 'lightgreen',
		animation: 'scale',
		draggable: false,
	});
}

function confirmBox(val, frmId){
	$.confirm({
		title: '',
		theme: 'modern',
		content: val,
		buttons: {
			확인: {
				btnClass: 'btn-blue',
				action: function () {
					document.getElementById(frmId).submit();
				}
			},
			취소: {
				btnClass: 'btn-ren',
				action: function () {
				}
			}
		}
	});
}

function confirmBox_ajax(val, frmId){
	$.confirm({
		title: '',
		theme: 'modern',
		content: val,
		buttons: {
			확인: {
				btnClass: 'btn-blue',
				action: function () {
					//document.getElementById(frmId).submit();
					
					var allParmData = $('#'+frmId).serialize();
					execCommAjax2("/"+$('form[name="'+frmId+'"]').attr('action'), allParmData);
					confirmSearch(frmId);
				}
			},
			취소: {
				btnClass: 'btn-ren',
				action: function () {
				}
			}
		}
	});
}

function confirmBox_Chk(val){
	$.confirm({
		title: '',
		theme: 'modern',
		content: val,
		buttons: {
			확인: {
				btnClass: 'btn-blue',
				action: function () {
					chk();
				}
			},
			취소: {
				btnClass: 'btn-ren',
				action: function () {
				}
			}
		}
	});
}

function confirmBox_frmChk(val){
	$.confirm({
		title: '',
		theme: 'modern',
		content: val,
		buttons: {
			확인: {
				btnClass: 'btn-blue',
				action: function () {
					chk_frm();
				}
			},
			취소: {
				btnClass: 'btn-ren',
				action: function () {
				}
			}
		}
	});
}

function confirmBox_del(val, frmId, ix){
	$.confirm({
		title: '',
		theme: 'modern',
		content: val,
		buttons: {
			확인: {
				btnClass: 'btn-blue',
				action: function () {
					document.getElementById(frmId).ix.value = ix;
					document.getElementById(frmId).submit();
				}
			},
			취소: {
				btnClass: 'btn-ren',
				action: function () {
				}
			}
		}
	});
}

function confirmBox_all_del(val, frmId){
	$.confirm({
		title: '',
		theme: 'modern',
		content: val,
		buttons: {
			확인: {
				btnClass: 'btn-blue',
				action: function () {
					var str = "";  
					num = 0;
					$("input:checkbox:checked").each(function (index) {  
						str += $(this).val() + ",";  
						num++;
					});  
					if(num == 0 || str == "0,"){
						alertBox("체크박스를 선택해주세요.");
					}else{
						document.getElementById(frmId).ix.value = str;
						document.getElementById(frmId).submit();
					}
				}
			},
			취소: {
				btnClass: 'btn-ren',
				action: function () {
				}
			}
		}
	});
}

function confirmBoxReception(val){
	$.confirm({
		title: '',
		theme: 'modern',
		content: val,
		buttons: {
			확인: {
				btnClass: 'btn-blue',
				action: function () {
					execReception();
				}
			},
			취소: {
				btnClass: 'btn-ren',
				action: function () {
				}
			}
		}
	});
}

function confirmBox_excel(val){
	$.confirm({
		title: '',
		theme: 'modern',
		content: val,
		buttons: {
			업로드: {
				btnClass: 'btn-blue',
				action: function () {
					excelUpload('excelFile');
				}
			},
			취소: {
				btnClass: 'btn-ren',
				action: function () {
				}
			}
		}
	});
}

function confirmBoxFunction(val, fun) {
	$.confirm({
		title: '',
		theme: 'modern',
		content: val,
		buttons: {
			확인: {
				//btnClass: 'btn-blue',
				action: fun
			}
		}
	});
}
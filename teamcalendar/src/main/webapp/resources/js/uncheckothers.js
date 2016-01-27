        function uncheckOthers(element) {
        var sharedIdPartName = $(element).attr('id').split(":")[3]
console.log(sharedIdPartName);
        var allcheckboxes = $("input[type='checkbox'][id*=" + sharedIdPartName+ "]");
		console.log(allcheckboxes.length);

        $.each(allcheckboxes, function(key, value) {

            if (($(value).attr('id').split(":")[3] == sharedIdPartName) && (value != element))  {
            
                $(value).removeAttr( "checked" );
				
				console.log($(value).parent().next().first().attr('class'));
				
				$(value).parent().next().attr("class", "ui-chkbox-box ui-widget ui-corner-all ui-state-default")
                $(value).parent().next().children().attr("class", "ui-chkbox-icon ui-icon ui-icon-blank ui-c")
            }
        });
    };
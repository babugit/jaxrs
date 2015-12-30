var pageobject = function() {
	var getUrl = null;
	var postUrl = null;
    
	var dataToPost = {
			id: 1,
			name: "Sam Holder",
			birthDate: "07/13/1990",			
			ranks: [
				{
					gameType: "Singles",
					rank	: 5
				},
				{
					gameType: "Doubles",
					rank	: 4
				}
			]
	};
	
	var done = function(data, status) {
		var type = Object.prototype.toString.call(data);
		var formatedText = '';
		
		if (type === "[object XMLDocument]"
				|| type === "[object Document]") {
			
			var text = (new XMLSerializer()).serializeToString(data);
			formatedText = vkbeautify.xml(text);
		}
		else {
			var text = JSON.stringify(data);
			formatedText = vkbeautify.json(text);
		}

		var date = new Date();
		var msg = 'Rest call successful '
			+ date.toLocaleDateString() + ' ' + date.toLocaleTimeString();
		$('#spanMessage').html(msg)
			.removeClass('msgError').addClass('msgNormal');
		$('#txtResult').html(formatedText);
	};
	
	var fail = function(xhr, status, error) {
		$('#txtResult').html('');
		
		var date = new Date();
		var msg = 'Rest call failed (Error code: ' + error + ') - '
			+ date.toLocaleDateString() + ' ' + date.toLocaleTimeString();
		$('#spanMessage').html(msg)
			.removeClass('msgNormal').addClass('msgError');;
		
	};
	
	var initpage = function(baseUrl) {
		getUrl = urlbase + 'api/getplayer/1/Sam%20Holder';
		postUrl = urlbase + 'api/postplayer';
		
		$('#btnGetXML').off('click');
		$('#btnGetJson').off('click');
		$('#btnPostJsonAcceptXML').off('click');
		$('#btnPOSTXMLAcceptJson').off('click');
		
		$('#btnGetXML').on('click', function() {
			$.ajax({
				url: getUrl,
				headers: {
					Accept: 'application/xml'
				}
			}).done(done).fail(fail);
		});
		
		$('#btnGetJson').on('click', function() {
			$.ajax({
				url: getUrl,
				headers: {
					Accept: 'application/json'
				}
			}).done(done).fail(fail);
		});

		$('#btnPostJsonAcceptXML').on('click', function() {
			$.ajax({
				url: postUrl,
				type: 'post',
				data: JSON.stringify(dataToPost),
				headers: {
					Accept: 'application/xml',
					'Content-Type': 'application/json'
				}
			}).done(done).fail(fail);
		});
		
		$('#btnPOSTXMLAcceptJson').on('click', function() {
			var xmlData = '<player>'
				+ jsonxml.json2xml(dataToPost) + '</player>';

			$.ajax({
				url: postUrl,
				type: 'post',
				data: xmlData,
				headers: {
					Accept: 'application/json',
					'Content-Type': 'application/xml'
				}
			}).done(done).fail(fail);
		});
	};
	
	return {
		initpage: initpage
	};
	
}();
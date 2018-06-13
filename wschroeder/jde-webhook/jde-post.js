var body = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:orac="http://oracle.e1.bssv.JP420000/">
				<soapenv:Header>
					<wsse:Security soapenv:mustUnderstand="1" xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:env="http://schemas.xmlsoap.org/soap/envelope/">
						<wsse:UsernameToken>
							<wsse:Username>alexa</wsse:Username>  
							<wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">alexa</wsse:Password>
						</wsse:UsernameToken>
					</wsse:Security>
				</soapenv:Header>
				<soapenv:Body>
					<orac:getItemListPrice>
						<businessUnit>M30</businessUnit>
						<item>
							<itemId>60038</itemId>
						</item>
					</orac:getItemListPrice>
				</soapenv:Body>
			</soapenv:Envelope>';

var postRequest = {
    host: "https://srvjde7.grupoassa.com:8103/DV920/SalesOrderManager 
    path: "",
    port: 80,
    method: "POST",
    headers: {
        'Content-Type': 'text/xml'
    }
};

var buffer = "";

var req = http.request( postRequest, function( res )    {

   console.log( res.statusCode );
   var buffer = "";
   res.on( "data", function( data ) { buffer = buffer + data; } );
   res.on( "end", function( data ) { console.log( buffer ); } );

});

req.on('error', function(e) {
    console.log('problem with request: ' + e.message);
});

req.write( body );
req.end();
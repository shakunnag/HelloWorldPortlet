function getReadyStateHandler(req, responseXmlHandler) {

	return function() {

		// If the request's status is "complete"
		if (req.readyState == 4) {

			// Check that a successful server response was received
			if (req.status == 200) {

				// Pass the XML payload of the response to the
				// handler function
				responseXmlHandler(req.responseXML);

			} else {

				// An HTTP problem has occurred
				alert("HTTP error: " + req.status);
			}
		}
	}
}
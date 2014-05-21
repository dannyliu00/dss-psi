var sellInNamespace;
var createSellInNamespace;

(function() {

    createSellInNamespace = function (errorMessage) {
		if (sellInNamespace === undefined) {
			var sectionDeriver = new namespaceUtility.SectionDeriver();
			var namespaceCreator = new namespaceUtility.NamespaceCreator(sectionDeriver);
            sellInNamespace = namespaceUtility.NamespaceInterface({}, namespaceCreator);
		} else {
			throw errorMessage;
		}
	};

}());

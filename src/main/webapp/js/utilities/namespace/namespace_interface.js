var namespaceUtility = namespaceUtility || {};
(function() {
	function NamespaceInterface(jsonNamespace, namespaceCreator) {
		return function(path) {
			return namespaceCreator.createNamespace(path, jsonNamespace);
		};
	}

	namespaceUtility.NamespaceInterface = NamespaceInterface;
}());
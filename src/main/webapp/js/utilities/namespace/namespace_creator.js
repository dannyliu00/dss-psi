var namespaceUtility = namespaceUtility || {};
(function() {
	function NamespaceCreator(sectionDeriver) {
		this.sectionDeriver = sectionDeriver;
	}

	function createNamespace(namespaceName, jsonNamespace) {
		var firstDelimiter = namespaceName.indexOf('.');

		if (firstDelimiter === -1) {
			return this.sectionDeriver.deriveSection(namespaceName, jsonNamespace);
		}

		var isolatedNamespace = namespaceName.slice(0, firstDelimiter);
		var onePastFirstDelimiter = firstDelimiter + 1;
		var remainingNamespace = namespaceName.slice(onePastFirstDelimiter);

		var derivedSection = this.sectionDeriver.deriveSection(isolatedNamespace, jsonNamespace);

		return this.createNamespaceInterface(remainingNamespace, derivedSection);
	}

	NamespaceCreator.prototype.createNamespaceInterface = createNamespace;
	NamespaceCreator.prototype.createNamespace = createNamespace;

	namespaceUtility.NamespaceCreator = NamespaceCreator;
}());
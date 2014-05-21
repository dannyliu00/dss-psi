var namespaceUtility = namespaceUtility || {};
(function() {
	function SectionDeriver() {

	}

	SectionDeriver.prototype.deriveSection = function(name, namespace) {
		if (namespace[name] === undefined) {
			namespace[name] = {};
		}
		return namespace[name];
	};

	namespaceUtility.SectionDeriver = SectionDeriver;
}());
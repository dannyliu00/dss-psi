(function() {
	describe('NamespaceInterface', function() {
		describe('namespace', function() {
			it('calls the namespace creator with the input path and the injected json namespace', function() {
				var expectedJsonNamespace = 'expected namespace';
				var namespaceCreator = jasmine.createSpyObj('NamespaceCreator', ['createNamespace']);
				var testObject = new namespaceUtility.NamespaceInterface(expectedJsonNamespace, namespaceCreator);

				var inputPath = 'input.path';

				var createdNamespace = 'created namespace';
				namespaceCreator.createNamespace.andReturn(createdNamespace);

				var actualNamespace = testObject(inputPath);

				expect(namespaceCreator.createNamespace).toHaveBeenCalledWith(inputPath, expectedJsonNamespace);
				expect(actualNamespace).toBe(createdNamespace);
			});
		});
	});
}());
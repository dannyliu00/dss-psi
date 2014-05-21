(function() {
	describe('NamespaceCreator', function() {
		describe('createNamespace', function() {
			var testObject;
			var sectionDeriver;

			beforeEach(function() {
				sectionDeriver = jasmine.createSpyObj('SectionDeriver', ['deriveSection']);
				testObject = new namespaceUtility.NamespaceCreator(sectionDeriver);

				testObject.createNamespaceInterface = jasmine.createSpy('createNamespaceInterface');
			});

			it('derives a section for the first namespace segment', function() {
				var namespacePath = 'my.path.remainder';
				var inputNamespace = 'input namespace namespace';
				var derivedSection = 'derived section';
				var expectedNamespace = 'expected namespace';

				sectionDeriver.deriveSection.andReturn(derivedSection);
				testObject.createNamespaceInterface.andReturn(expectedNamespace);

				var actualSection = testObject.createNamespace(namespacePath, inputNamespace);

				expect(sectionDeriver.deriveSection).toHaveBeenCalledWith('my', inputNamespace);
				expect(testObject.createNamespaceInterface).toHaveBeenCalledWith('path.remainder', derivedSection);
				expect(actualSection).toBe(expectedNamespace);
			});

			it('returns the derived section upon the last namespace segment', function() {
				var namespacePath = 'my';
				var inputNamespace = 'input namespace namespace';
				var derivedSection = 'derived section';

				sectionDeriver.deriveSection.andReturn(derivedSection);

				var actualSection = testObject.createNamespace(namespacePath, inputNamespace);

				expect(sectionDeriver.deriveSection).toHaveBeenCalledWith('my', inputNamespace);
				expect(testObject.createNamespaceInterface).not.toHaveBeenCalled();
				expect(actualSection).toBe(derivedSection);
			});
		});

		describe('createNamespaceInterface', function() {
			it('is the same method as createNamespace', function() {
				var testObject = new namespaceUtility.NamespaceCreator();
				expect(testObject.createNamespace).toBe(testObject.createNamespaceInterface);
			});
		});
	});
}());
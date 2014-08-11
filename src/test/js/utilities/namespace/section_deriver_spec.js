(function () {
    describe('SectionDeriver', function () {
        var testObject, sectionName;

        beforeEach(function () {
            testObject = new namespaceUtility.SectionDeriver();
            sectionName = 'animals';
        });

        it('returns the same created section if it didnt already exist', function () {
            var existingEmptyNamespace = {};
            var expectedCreatedSection = {};

            var actualSection = testObject.deriveSection(sectionName, existingEmptyNamespace);

            expect(existingEmptyNamespace[sectionName]).toEqual(expectedCreatedSection);
            expect(actualSection).toEqual(expectedCreatedSection);

            // test that both sections are the same instance
            var myValue = 'my value';
            existingEmptyNamespace[sectionName].myKey = myValue;
            expect(actualSection.myKey).toBe(myValue);
        });

        it('returns the already existing section', function () {
            var existingValue = 'my value';
            var existingNamespace = {};
            existingNamespace[sectionName] = existingValue;

            var actualSection = testObject.deriveSection(sectionName, existingNamespace);

            expect(actualSection).toBe(existingValue);
        });
    });
}());
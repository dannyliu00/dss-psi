var sellInNamespace;

describe('PolarisNamespace', function () {
    var oldConstructor;
    var errorMessage;

    beforeEach(function () {
        oldConstructor = namespaceUtility.NamespaceInterface;
        namespaceUtility.NamespaceInterface = jasmine.createSpy('NamespaceInterface');
        sellInNamespace = undefined;
        errorMessage = 'expected error message';
    });

    afterEach(function () {
        namespaceUtility.NamespaceInterface = oldConstructor;
    });

    it('creates a namespace if one doesnt already exist', function () {
        var expectedNamespace = 'expected namespace';

        namespaceUtility.NamespaceInterface.andReturn(expectedNamespace);

        createSellInNamespace();

        expect(namespaceUtility.NamespaceInterface).toHaveBeenCalled();
        expect(sellInNamespace).toEqual(expectedNamespace);
    });

    it('throws an error if the namespace already exists', function () {
        sellInNamespace = 'already created namespace';

        var testFunction = function () {
            createSellInNamespace(errorMessage);
        };

        expect(testFunction).toThrow(errorMessage);

        expect(namespaceUtility.NamespaceInterface).not.toHaveBeenCalled();
    });
});
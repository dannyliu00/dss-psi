(function() {
    var commonHeader = sellInNamespace('polaris.directives.commonHeader');

    describe('CommonHeaderController', function() {
        var scope, expectedTitle, ctrl;

        beforeEach(function() {
            scope = {};
            expectedTitle = 'U.T. Expected Title';
            ctrl = new commonHeader.CommonHeaderController(scope, expectedTitle);
        });

        describe('constructor', function() {
            it('sets attributes on scope', function() {
                expect(ctrl.scope.appTitle).toEqual(expectedTitle);
            });
        });

    });
})();

(function() {
    var commonHeader = sellInNamespace('polaris.directives.commonHeader');

    describe('CommonHeaderController', function() {
        var scope, expectedTitle, ctrl;

        beforeEach(function() {
            angular.mock.module('polaris.directives.commonHeader');

            expectedTitle = 'U.T. Expected Title';
        });

        beforeEach(inject(function ($rootScope) {
            scope = $rootScope.$new();
        }));

        describe('constructor', function() {
            it('sets attributes on scope', inject(function($rootScope) {
                ctrl = new commonHeader.CommonHeaderController(scope, expectedTitle);

                $rootScope.$digest();

                expect(scope.appTitle).toEqual(expectedTitle);
            }));
        });
    });
})();

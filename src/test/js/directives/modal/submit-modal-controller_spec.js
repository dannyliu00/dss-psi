(function () {
    var submitValues = sellInNamespace('sellIn.directives.submitValues');

    describe('SubmitController', function () {
        var scope, modalInstance, dealerProfileResource, data;
        var ctrl;

        beforeEach(function () {
            angular.mock.module('sellIn.directives.submitValues');

            angular.mock.module(function ($provide) {
                dealerProfileResource = jasmine.createSpyObj('dealerProfileResource', ['submit']);
                $provide.decorator('dealerProfileResource', [function () {
                    return dealerProfileResource;
                }]);

                modalInstance = jasmine.createSpyObj('modalInstance', ['open', 'close']);
                data = [
                    {id: 'ID1'},
                    {id: 'ID2'}
                ];
            });
        });

        describe('constructor', function () {
            it('creates functions for scope', inject(function ($rootScope, dealerProfileResource) {
                ctrl = submitValues.SubmitController($rootScope, modalInstance, dealerProfileResource, data);

                expect($rootScope.submit).toBeDefined();
                expect($rootScope.cancel).toBeDefined();
            }));
        });
    });
})();
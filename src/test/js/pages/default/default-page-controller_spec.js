describe('DefaultPageController', function() {
    var defaultPage = sellInNamespace('sellIn.pages.default');

    var appRoleResource, expectedRole, expectedRoleDeferred, location,  dealerUrl, dsmUrl, dsmRoleId;
    var ctrl;

    beforeEach(function() {
        angular.mock.module('sellIn.pages.default');

        angular.mock.module(function ($provide) {
            appRoleResource = jasmine.createSpyObj('appRoleResource', ['get']);
            $provide.decorator('appRoleResource', [function () {
                return appRoleResource;
            }]);

            location = jasmine.createSpyObj('AngularLocation', ['path']);
            $provide.decorator('$location', function() {
                return location;
            });
        });
    });

    beforeEach(inject(function ($q, appRoleResource) {
        expectedRoleDeferred = $q.defer();
        appRoleResource.get.andReturn(expectedRoleDeferred.promise);
    }));

    describe('constructor', function() {
        it('initializes role on scope', inject(function($rootScope, $location, appRoleResource) {
            dealerUrl = '/dealers/:dealerId';
            dsmUrl = '/dsm/:id';
            expectedRole = {
                dealerId: 'UTID',
                userName: 'utUser',
                customerClass: 'UTDLR'
            };
            expectedRoleDeferred.resolve(expectedRole);

            ctrl = new defaultPage.DefaultPageController($rootScope, $location, appRoleResource, dealerUrl, dsmUrl, dsmRoleId);

            expect(appRoleResource.get).toHaveBeenCalled();

            $rootScope.$digest();
            expect($rootScope.role.dealerId).toEqual(expectedRole.dealerId);
            expect($rootScope.role.userName).toEqual(expectedRole.userName);
            expect($rootScope.role.customerClass).toEqual(expectedRole.customerClass);
        }));

        it('calls location service based on dealer user role', inject(function($rootScope, $location, appRoleResource) {
            expectedRole = {
                dealerId: 'UTID',
                userName: 'utUser',
                customerClass: 'UTDLR'
            };

            expectedRoleDeferred.resolve(expectedRole);

            ctrl = new defaultPage.DefaultPageController($rootScope, $location, appRoleResource, dealerUrl, dsmUrl, dsmRoleId);

            $rootScope.$digest();

            var expectedDealerUrl = dealerUrl.replace(':dealerId', expectedRole.dealerId);
            expect($location.path).toHaveBeenCalledWith(expectedDealerUrl);
        }));

        it('calls location service based on dsm user role', inject(function($rootScope, $location, appRoleResource) {
            expectedRole = {
                dealerId: 'UTID',
                userName: 'utUser',
                customerClass: 'UTDSM'
            };
            dsmRoleId = expectedRole.customerClass;

            expectedRoleDeferred.resolve(expectedRole);

            ctrl = new defaultPage.DefaultPageController($rootScope, $location, appRoleResource, dealerUrl, dsmUrl, dsmRoleId);

            $rootScope.$digest();

            var expectedDsmUrl = dsmUrl.replace(':id', expectedRole.dealerId);
            expect($location.path).toHaveBeenCalledWith(expectedDsmUrl);
        }));
    });

});

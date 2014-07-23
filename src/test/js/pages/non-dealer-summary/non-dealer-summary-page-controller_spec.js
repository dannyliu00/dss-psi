(function() {
    var nonDealerSummary = sellInNamespace('sellIn.pages.nondealersummary');

    describe('NonDealerSummaryController', function () {
        var scope, routeParams, $location, appRoleResource, dsmProfilesResource, rsmProfilesResource, expectedId;
        var expectedProfilesDeferred, expectedRoleDeferred, expectedRole;
        var ctrl;

        beforeEach(function() {
            angular.mock.module('sellIn.pages.nondealersummary');

            angular.mock.module(function($provide) {
                appRoleResource = jasmine.createSpyObj('appRoleResource', ['get']);
                $provide.decorator('appRoleResource', [function () {
                    return appRoleResource;
                }]);

                dsmProfilesResource = jasmine.createSpyObj('dsmProfilesResource', ['query']);
                $provide.decorator('dsmProfilesResource', [function () {
                    return dsmProfilesResource;
                }]);

                rsmProfilesResource = jasmine.createSpyObj('rsmProfilesResource', ['query']);
                $provide.decorator('rsmProfilesResource', [function () {
                    return rsmProfilesResource;
                }]);

                $location = jasmine.createSpyObj('$location', ['path']);
                $provide.decorator('$location', [function () {
                    return $location;
                }]);
            });

            expectedId = 111;
            routeParams = {id: expectedId};
        });

        beforeEach(inject(function ($q, $rootScope, appRoleResource, dsmProfilesResource, rsmProfilesResource) {
            expectedRoleDeferred = $q.defer();
            appRoleResource.get.andReturn(expectedRoleDeferred.promise);

            expectedProfilesDeferred = $q.defer();
            dsmProfilesResource.query.andReturn(expectedProfilesDeferred.promise);
            rsmProfilesResource.query.andReturn(expectedProfilesDeferred.promise);
        }));

        describe('constructor', function() {
            it('sets an array of profiles on scope for DSM', inject(function($rootScope, $location, appRoleResource, dsmProfilesResource, rsmProfilesResource, profilePageUrl, productTabs) {
                expectedRole = {dsm: true, rsm: false, sessionDetail: {ATV: 'Y'}};
                expectedRoleDeferred.resolve(expectedRole);
                var expectedDsm = {dsmId: expectedId, type: '2'};
                expectedProfilesDeferred.resolve(expectedDsm);

                ctrl = new nonDealerSummary.NonDealerSummaryController($rootScope, routeParams, $location, dsmProfilesResource, appRoleResource, rsmProfilesResource, profilePageUrl, productTabs);

                $rootScope.$digest();

                expect(appRoleResource.get).toHaveBeenCalled();
                expect($rootScope.role).toBeDefined();

                expect(dsmProfilesResource.query).toHaveBeenCalledWith(expectedDsm);
                expect($rootScope.profiles).toBeDefined();
            }));
        });

        describe('constructor', function() {
            it('sets an array of profiles on scope for RSM', inject(function($rootScope, $location, appRoleResource, dsmProfilesResource, rsmProfilesResource, profilePageUrl, productTabs) {
                expectedRole = {dsm: false, rsm: true, sessionDetail: {ATV: 'Y'}};
                expectedRoleDeferred.resolve(expectedRole);
                var expectedRsm = {rsmId: expectedId, type: '2'};
                expectedProfilesDeferred.resolve(expectedRsm);

                ctrl = new nonDealerSummary.NonDealerSummaryController($rootScope, routeParams, $location, dsmProfilesResource, appRoleResource, rsmProfilesResource, profilePageUrl, productTabs);

                $rootScope.$digest();

                expect(appRoleResource.get).toHaveBeenCalled();
                expect($rootScope.role).toBeDefined();

                expect(rsmProfilesResource.query).toHaveBeenCalledWith(expectedRsm);
                expect($rootScope.profiles).toBeDefined();
            }));
        });
    });
})();
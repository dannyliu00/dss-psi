(function() {
    var commonHeader = sellInNamespace('polaris.directives.commonHeader');

    describe('CommonHeaderController', function() {
        var scope, expectedTitle, ctrl;
        var menuResource, appRoleResource, appAttributeResource;
        var expectedMenuDeferred, expectedRoleDeferred, expectedAttributeDeferred;

        beforeEach(function() {
            angular.mock.module('polaris.directives.commonHeader');

            angular.mock.module(function ($provide) {
                appRoleResource = jasmine.createSpyObj('appRoleResource', ['get']);
                $provide.decorator('appRoleResource', [function () {
                    return appRoleResource;
                }]);

                menuResource = jasmine.createSpyObj('menuResource', ['get']);
                $provide.decorator('menuResource', function() {
                    return menuResource;
                });

                appAttributeResource = jasmine.createSpyObj('appAttributeResource', ['get']);
                $provide.decorator('appAttributeResource', function() {
                    return appAttributeResource;
                });
            });

            expectedTitle = 'U.T. Expected Title';
        });

        beforeEach(inject(function ($q, $rootScope, appRoleResource, menuResource, appAttributeResource) {
            expectedRoleDeferred = $q.defer();
            appRoleResource.get.andReturn(expectedRoleDeferred.promise);

            expectedMenuDeferred = $q.defer();
            menuResource.get.andReturn(expectedMenuDeferred.promise);

            expectedAttributeDeferred = $q.defer();
            appAttributeResource.get.andReturn(expectedAttributeDeferred.promise);

            scope = $rootScope.$new();
        }));

        describe('constructor', function() {
            it('sets attributes on scope', inject(function($rootScope, menuResource, appRoleResource, appAttributeResource) {
                var dealerId = 999;
                var dealerName = 'UT Dealer Name';
                var isSalesman, isDealer, isDIS, isRSM;
                isSalesman = isDIS = isRSM = true;
                isDealer = false;
                var expectedRole = {
                    salesman: isSalesman,
                    dealer: isDealer,
                    userRole: 'DIS',
                    customerClass: 'RSM',
                    sessionDetail: {
                        DealerID: dealerId,
                        DealerName: dealerName
                    }
                };
                var expectedAttr = {};
                var expectedMenuData = {data: [{
                    category: [{
                        colName: 'mktg',
                        displayName: 'Marketing',
                        displayOrder: 1,
                        mainCategory: 1,
                        menuLinks:  [{
                            colName: 'Mktg',
                            displayName: 'Display Name',
                            linkTarget: 'http://ut.local/polaris',
                            linkType: 'Local',
                            linkWindow: '',
                            mainCategory: true,
                            parentName: 'Marketing',
                            subCatHpId: 0,
                            subMenuLinks: []
                        }]
                    }]
                    }]
                };

                expectedRoleDeferred.resolve(expectedRole);
                expectedAttributeDeferred.resolve(expectedAttr);
                expectedMenuDeferred.resolve(expectedMenuData);

                ctrl = new commonHeader.CommonHeaderController(scope, expectedTitle, menuResource, appRoleResource, appAttributeResource);

                $rootScope.$digest();

                expect(scope.appTitle).toEqual(expectedTitle);
                expect(scope.isSalesman).toEqual(isSalesman);
                expect(scope.isDealer).toEqual(isDealer);
                expect(scope.isDIS).toEqual(isDIS);
                expect(scope.isRSM).toEqual(isRSM);
                expect(scope.dealerId).toEqual(dealerId);
                expect(scope.dealerName).toEqual(dealerName);
                expect(scope.attributes).toEqual(expectedAttr);
            }));
        });

    });
})();

(function () {
    var mainMenu = sellInNamespace('polaris.directives.menu');

    describe('MainMenuController', function () {

        var appRoleResource, menuResource, appAttributeResource;
        var roleDeferred, menuDeferred, attributesDeferred;
        var expectedRole, expectedAttributes, expectedMenuData;
        var scope, isSalesman, isDealer, isDIS, isRSM, dealerId, dealerName;
        var ctrl;

        beforeEach(function () {
            dealerId = 999;
            dealerName = 'UT Dealer Name';
            isSalesman = isDIS = isRSM = true;
            isDealer = false;
            expectedRole = {
                salesman: isSalesman,
                dealer: isDealer,
                userRole: 'DIS',
                customerClass: 'RSM',
                sessionDetail: {
                    DealerID: dealerId,
                    DealerName: dealerName
                }
            };
            expectedAttributes = {};
            expectedMenuData = {data: [
                {
                    category: [
                        {
                            colName: 'mktg',
                            displayName: 'Marketing',
                            displayOrder: 1,
                            mainCategory: 1,
                            menuLinks: [
                                {
                                    colName: 'Mktg',
                                    displayName: 'Display Name',
                                    linkTarget: 'http://ut.local/polaris',
                                    linkType: 'Local',
                                    linkWindow: '',
                                    mainCategory: true,
                                    parentName: 'Marketing',
                                    subCatHpId: 0,
                                    subMenuLinks: []
                                }
                            ]
                        }
                    ]
                }
            ]
            };
        });

        beforeEach(function () {
            angular.mock.module('polaris.directives.menu');

            angular.mock.module(function ($provide) {
                appRoleResource = jasmine.createSpyObj('appRoleResource', ['get']);
                $provide.decorator('appRoleResource', [function () {
                    return appRoleResource;
                }]);

                menuResource = jasmine.createSpyObj('menuResource', ['get']);
                $provide.decorator('menuResource', function () {
                    return menuResource;
                });

                appAttributeResource = jasmine.createSpyObj('appAttributeResource', ['get']);
                $provide.decorator('appAttributeResource', function () {
                    return appAttributeResource;
                });
            });
        });

        beforeEach(inject(function ($q, $rootScope, appRoleResource, menuResource, appAttributeResource) {
            roleDeferred = $q.defer();
            appRoleResource.get.andReturn(roleDeferred.promise);

            menuDeferred = $q.defer();
            menuResource.get.andReturn(menuDeferred.promise);

            attributesDeferred = $q.defer();
            appAttributeResource.get.andReturn(attributesDeferred.promise);

            scope = $rootScope.$new();
        }));

        describe('constructor', function () {
            it('sets variables on scope', inject(function ($rootScope, appRoleResource, menuResource, appAttributeResource) {
                roleDeferred.resolve(expectedRole);
                attributesDeferred.resolve(expectedAttributes);
                menuDeferred.resolve(expectedMenuData);

                ctrl = new mainMenu.MainMenuController(scope, appRoleResource, menuResource, appAttributeResource);

                $rootScope.$digest();

                expect(appRoleResource.get).toHaveBeenCalled();
                expect(menuResource.get).toHaveBeenCalled();
                expect(appAttributeResource.get).toHaveBeenCalled();
                expect(scope.isSalesman).toEqual(isSalesman);
                expect(scope.isDealer).toEqual(isDealer);
                expect(scope.isDIS).toEqual(isDIS);
                expect(scope.isRSM).toEqual(isRSM);
                expect(scope.dealerId).toEqual(dealerId);
                expect(scope.dealerName).toEqual(dealerName);
                expect(scope.attributes).toEqual(expectedAttributes);

            }));
        });
    });
})();
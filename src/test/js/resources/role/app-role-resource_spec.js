describe('AppRoleResource', function() {
    var httpBackend;

    beforeEach(function() {
        angular.mock.module('sellIn.resources.role');

        angular.mock.inject(function($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    describe('get', function() {
        it('returns a promise containing the user role for the app', inject(function(appRoleResource){
            var expectedRole = {name: 'UT'};
            httpBackend.when('GET').respond(expectedRole);
            httpBackend.expectGET;

            var promise = appRoleResource.get();

            promise.then(function(role) {
                expect(role.name).toEqual(expectedRole.name);
            });
            httpBackend.flush();
        }));
    });
});
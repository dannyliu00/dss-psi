describe('ReasonCodeResource', function () {
    var httpBackend;

    beforeEach(function () {
        angular.mock.module('sellIn.resources.reasoncode');

        angular.mock.inject(function ($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    describe('get', function () {
        it('returns a promise containing the reason codes based on the user role', inject(function (reasonCodeResource, reasonCodeUrl) {
            var roleId = 4;
            var role = {roleId: roleId};
            var expectedRequest = reasonCodeUrl.replace(':roleId', roleId);
            var expectedList = [
                {name: 'U.T. reason'}
            ];

            httpBackend.when('GET', expectedRequest).respond(expectedList);
            httpBackend.expectGET;

            var promise = reasonCodeResource.query(role);

            promise.then(function (reasonCodes) {
                expect(reasonCodes[0].name).toEqual(expectedList[0].name);
            });
            httpBackend.flush();
        }));
    });
});
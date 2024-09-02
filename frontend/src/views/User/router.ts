// signRouter.ts

import userAuth from './Auth/router.ts'

export default [

    {
        path: '/user/auth',
        name: 'user-auth',
        children:userAuth,
    },

];

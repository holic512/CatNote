import {UnwrapRef} from "vue";

export function register(a, b, c) {
    console.log(a, b, c)
}

export function login(a: UnwrapRef<string>, value: UnwrapRef<string>, email: string) {
    console.log(a, value, email)
}

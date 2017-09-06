// !LANGUAGE: +ContractEffects

import kotlin.effects.dsl.*

fun foo(boolean: Boolean) {
    contract {
        <!ERROR_IN_CONTRACT_DESCRIPTION!>(returns() implies (boolean)) <!UNRESOLVED_REFERENCE!>implies<!> (!boolean)<!>
    }
}
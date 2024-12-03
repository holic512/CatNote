<script setup lang="ts">

import {DatePicker} from "v-calendar";

import 'v-calendar/style.css';
const date = new Date()
</script>

<template>
  <div style="padding: 4px">
    <DatePicker v-model="date" style="width: 99%;"/>
  </div>
</template>

<style scoped>

</style>
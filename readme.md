# Nutritional

A datapack-driven nutrient and dietary-effect system for Minecraft 1.21.1 on NeoForge. Modern rewrite of [WesCook's Nutrition](https://github.com/WesCook/Nutrition), redesigned around 1.21 data attachments, datapack registries, NeoForge DataMaps, and the modern attribute-modifier system.

[Releases](https://github.com/Saereth/Nutrition-UE/releases) | [Changelogs](metadata/changelogs/)

## About

Track per-player intake across five food groups (dairy, fruit, grain, protein, vegetable) and let nutrient balance shape gameplay through:

- **Diet tiers** — the player's overall balance is graded into tiers (`starving` → `malnourished` → `surviving` → `nourished` → `gourmand`). Each tier attaches its own bundle of attribute modifiers. Tiers and their thresholds are entirely datapack-defined.
- **Effects** — datapack-driven mappings from nutrient thresholds to mob effects (vanilla or modded). Any of four detection modes: `any`, `average`, `all`, or `cumulative`.
- **Sustained rewards** — long-game streak system. Stay in a qualifying tier for N consecutive Minecraft days and earn permanent attribute bonuses. Lost on death or on dropping below a floor tier (configurable per reward).
- **Custom attributes** — `nutritional:nutrient_absorption` (eating well = food gives more) and `nutritional:nutrient_decay_rate` (eating well = nutrients drain slower). Standard NeoForge attributes — modifiable by equipment, mob effects, other mods.

The mod ships sensible defaults out of the box. Every numeric tuning value, every effect threshold, every tier bundle is overridable from a datapack.

## Data model

Five first-class datapack registries:

| Registry | Path |
| --- | --- |
| `nutritional:nutrient` | `data/<pack>/nutritional/nutrient/<id>.json` |
| `nutritional:effect` | `data/<pack>/nutritional/effect/<id>.json` |
| `nutritional:food_hint` | `data/<pack>/nutritional/food_hint/<id>.json` |
| `nutritional:diet_tier` | `data/<pack>/nutritional/diet_tier/<id>.json` |
| `nutritional:sustained_reward` | `data/<pack>/nutritional/sustained_reward/<id>.json` |

Item membership comes from tags (`#nutritional:nutrient/<id>`). Per-item scale overrides come from a NeoForge DataMap (`nutritional:nutrient_scales` on the `minecraft:item` registry). Pack authors rarely need to touch Java.

## Commands

- `/nutritional get|set|add|subtract|reset <player> <nutrient> [<value>]`
- `/nutritional reload` — reloads datapacks
- `/nutritional-food` — prints nutrient yield for the held item

## Client UX

- Food tooltips show which nutrients the item provides and at what scale.
- A small HUD widget in the top-right shows the current diet tier (translated, themed icon, configurable visibility per tier).
- Default keybind **N** opens a screen with bars for every nutrient + the active tier label.

Client toggles for tooltip / HUD / GUI button live in `nutritional-client.toml`. Server tunables (decay rates, death penalty, multipliers, logging) live in `nutritional-server.toml`.

## Credits

- [WesCook](https://github.com/WesCook) — original [Nutrition](https://github.com/WesCook/Nutrition) author. Original 1.12 mod inspired this rewrite's core concepts (nutrients, thresholds, decay tied to hunger).
- Original 1.12 contributors listed in this repo's git history (preserved on the `1.12` branch).

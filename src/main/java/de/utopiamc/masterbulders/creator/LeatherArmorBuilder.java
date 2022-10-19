package de.utopiamc.masterbulders.creator;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class LeatherArmorBuilder {

    private ItemStack itemStack;
    private LeatherArmorMeta itemMeta;

    public LeatherArmorBuilder(Material material){
        itemStack = new ItemStack(material);
        itemMeta = (LeatherArmorMeta) itemStack.getItemMeta();
    }

    public LeatherArmorBuilder(Material material, Integer amount, byte b){
        itemStack = new ItemStack(material, amount, b);
        itemMeta = (LeatherArmorMeta) itemStack.getItemMeta();
    }

    public LeatherArmorBuilder addEnchantment(Enchantment enchantment, Integer level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public LeatherArmorBuilder setLore(List<String> lore) {
        itemMeta.setLore(lore);
        return this;
    }

    public LeatherArmorBuilder addLineLore(String loreLine) {
        List<String> lore;
        if (itemMeta.hasLore()){
            lore = itemMeta.getLore();
        }else {
            lore = new ArrayList<>();
        }
        lore.add(loreLine);
        itemMeta.setLore(lore);

        return this;
    }

    public LeatherArmorBuilder setColor(Color color){
        itemMeta.setColor(color);
        return this;
    }

    public LeatherArmorBuilder setHideFlags() {
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        return this;
    }

    public LeatherArmorBuilder setDisplayName(String name) {
        itemMeta.setDisplayName(name);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}


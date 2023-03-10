@extends('app')

@section('content')

	<div class="page-header"><h4>FAQ</h4></div>

	<div>

<br>Part 1: Client-side issues before logging in​<br>
<br>"This program is unavailable in your country"<br>
Make sure your client is fully installed and up to date with the version our server is using, if it is, go to Aion\L10N\ENU, select everything in there (Ctrl+A) and copy it, go back to Aion\L10N, select the folder of the language your windows is using and paste what you copied, then run the Aion Genesis Launcher and update if you need to.

<br>For the language folders:<br>
<br>DEU is German<br>
<br>ENU is English<br>
<br>FRA is French<br>
<br>RUS is Russian<br>

<br>If that doesn't work then if repair your client and use the 5.0 to 4.9 downgrade located on the homepage. Try to delete the L10N folder and re-download and replace it through the downgrade. Delete your L10N folder, open the NCsoft launcher and repair it. Also make sure your client is NA region as Gameforge isn't supported.<br>

<br>"No game server is available to the authorization server"<br>
If you have any ping reducers or game boosters (WTFast etc), turn them off completely by making sure they aren't even running in the background and try again. Try turning off your antivirus or firewall too since the error happens when you cannot establish a connection with the server. If none of these work, re-install the DreamAion launcher, run it as admin and let it update because you're missing the files needed to connect.<br>

<br>There have also been rare cases where after logging in the game would tell the player "You are already logged in" and when logging in again it would show this problem, and the solution was to just wait a minute after seeing "you are already logged in" and then typing your info and logging in again. This is mainly a bin32 issue, so please try to replace that.<br> 

<br>"Nothing happens after I accept the Terms & Conditions"<br>
<br>This means that you did not downgrade properly, or perhaps your firewall/antivirus is interfering.<br> 


​<br>"CRITICAL ERROR: Error loading DLL: Game.dll<br>
<br> Apply our downgrade and replace the Bin32 folder because there are complications with it. Your bin32 is most likely corrupt. Delete your Bin32 folder and apply our bin32 from the downgrade.<br>

​
<br>"You have been disconnected from the server" after logging in<br>
<br>The servers are either down or your connection to the server is stuck, check if you are running any ping reducers or game boosters and try again, also turn off your firewall and antivirus and see if that works. some items may crash your character in which a GM will need to delete an item(s) from your inventory. Your character may also be char banned. However, if you are disconnected from every character on every account, you are mac banned.  <br> 
​

<br>"Aion[1001]. You cannot run Aion with the version of DirectX currently installed<br>
<br>Aion requires the DirectX version released after June, 2008. Download DirectX through Microsoft Web Installer and try again.<br>

<br>"You cannot access the server through this IP"
<br>This means your account is either banned or you are IP banned. You may dispute your ban on our forums in the "Dispute" section.<br> 


<br>Part 2: Client-side issues after logging in, In-Game Questions and Other Questions<br>


<br>"You cannot create any more characters"<br>
<br>Restart your client. When your account is full and has 8 characters which is the maximum an account can have, when you delete a character you have to restart the client for it to recognize an empty spot. The same thing happens when you are trying to delete a character and you already left the legion, it requires a client restart.<br>

<br>"I cannot hide my legion cloak"<br>
<br>Yes, if you only downloaded bin32 and not L10N and Data, you will experience in game related problems. We are 4.9 and you have 5.0 user interface. Which means titles will not work and profile user interface prblems such as unable to hide legion cloak. To solve this you will need L10N and Data also (full downgrade)<br>

<br>"Failed to create character"<br>
<br>Dual race creation is bugged here, so you have to make a seperate account if you want to play another faction. Make sure you have all elyos characters for example on that account. It will give this error when you try to create an asmodian character. We will work on getting this fixed.<br>

<br>In-Game Questions:<br>
<br>"I just created my character, how do I gear up? Why can't I expand my cube or have max crafting skills?"<br>
<br>We are an instant level 65 full stigma server. You MUST type .skills command to be granted full stigma skills and be able to equip gears. After that, type .cube to expand your inventory and .job to recieve max crafting skills. Your inventory will only contain scrolls and consumables because you will need to use the .give command to add consumables.<br> 

<br>"How come other people have Elite Arbiter gears and SEA (Special Elite Ambassador) accessories and I don't?"<br>
<br>We have an event where if you invite 5 friends or more you and all your friends will be granted with blocked items. More details about how to sign up can be found <a href="#">here.</a> Or they donate for elite arbiter sets. SEA accessories require donation. <br>

<br>"How do I amplify my set and weapon?"<br>
<Br>Currently you may vote for an unlimited amount of times, with 20 credits per vote. Amplification tools/stones, Tempering Solution and Omega enchantment stones cost 200 credits in our webshop which can be obtained by voting. To amplify, right click the amplfiication tool, then right click a piece of your armor, a window will show up. Place the amplfiication stone in the 2nd splot and click amplify. Then it will say your gear has been successfully amplified. Use omega enchantment stones to get your set and weapon to 20 or 25. After that it seemes bugged, so please only enchant to those numbers.<br>

<br>"How do I vote correctly?"<br>
<br>To vote correctly, you must login to the account panel on aion-genesis.tk then click the "Vote" tab. Where it says "Vote for Us!" on the homepage, you can ignore that as you will not recieve credits by voting on the homepage. Click the links one by one in the account panel in the vote tab (will not open in a new tab.) Which means you will need to revisit dreamaion.com for each vote link after you have voted to actually receive credits. Vote by <a href="http://www.aion-genesis.tk/vote">clicking here</a><br>

<br>"What are forbidden items?"<br>
<br>Forbidden items may be test items that can be abused easily in PvPvE. They are also items that require donation or by voting to promote server content and donations for improvement of the server. The list of forbidden items can be found <a href="#">clicking here.</a><br>

<br>Other Questions<br>
<br>"What is Premium and VIP? How do I get it? What are the benefits?"<br>
<br>Premium and VIP membership offer higher server rates and discounts in the webshop. To get Premium/VIP, you can either vote or donate for it. WHen you have enough credits you can login to the account panel and click "My Account."The duration is 60 days. The discount in the webshop is 5% for Premium and 10% for VIP. You can also trade certain untradables and store items in your account warehouse. A blue diamond tag next to someone's name they are Premium and a purple diamong tag means they are VIP.<br>

<br>"How do I get GP?"<br>
<br>GP, or Glory Points is a ranking system in which points can be obtained from siege and daily GP quests. We have high GP rates here, however siege is not working at the moment so we give 5k GP free for all players.<br>

<br>"What is Xform? How do I use it?"<br>
<br>Xform is a unique 5 star officer skill that lets you transform into a Guardian using 50-75 Seeds of Transformation. You get about 100k+ HP and some skills like Abyssal Fury. This cannot be used in Sanctum/Pandaemonium, only areas like Inggisson or Gelkmaros and has a 2 hour cooldown. Please note that Xform is NOT allowed in events hosted by GMs, only open world PvPvE.<br>


<br>"What are Event Masters and how do I schedule an audition?"
<br>Event masters are players that host events. They are not GMs, they distrubite their own prizes otherwise that would take the purpose out of GM events. You may schedule an audition <a href="#">here</a> and read more about what they're responsible for <a href="#">here</a><br>
<br>"How do I donate to support DreamAion's future development?"<br>
<br>Log into your account on dreamaion.com, click donate and you may donate using Paypal, Payment Wall or Super rewards. You can donate by mobile, credit card, paypal etc. You will be rewarded with credits and will be able to purchase items from our webshop. Keep in mind that all items from webshop are sent via express mail. If you are logged onto your character when you made the purchase, go to character selection and back to recieve the shugo express mail.<br>


<br>If you have any other questions or concers, please do not hesitate to contact myself or another staff on our forums.<br> 

<br>Best Regards!<br>
	</div>
@endsection
